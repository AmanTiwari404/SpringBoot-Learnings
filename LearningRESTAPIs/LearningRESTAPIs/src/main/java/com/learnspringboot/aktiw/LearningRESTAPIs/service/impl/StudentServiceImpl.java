package com.learnspringboot.aktiw.LearningRESTAPIs.service.impl;

import com.learnspringboot.aktiw.LearningRESTAPIs.dto.AddStudentRequestDto;
import com.learnspringboot.aktiw.LearningRESTAPIs.dto.StudentDto;
import com.learnspringboot.aktiw.LearningRESTAPIs.entity.Student;
import com.learnspringboot.aktiw.LearningRESTAPIs.repository.StudentRepository;
import com.learnspringboot.aktiw.LearningRESTAPIs.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;      //here service layer connected(talking/using) persistence layer
    private final ModelMapper modelMapper;           //modelmapper is used in Spring Boot applications to simplify the process of mapping objects from one type to another.(here it is used to convert student to studentDto return type)

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();     //It giving us student but we have to return student dto so we have to covert student -> student dto
        return (List<StudentDto>) students.stream().map(student -> modelMapper.map(student, StudentDto.class)).toList();
    }

    @Override
    public StudentDto getStudentById(Long id) {
        Student student =  studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Student not found with Id: " + id));
        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public StudentDto createNewStudent(AddStudentRequestDto addStudentRequestDto) {
        Student newStudent = modelMapper.map(addStudentRequestDto, Student.class);       //save to java memory
        Student student = studentRepository.save(newStudent);             //save to database
        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public void deleteStudentByID(Long id) {
        if(!studentRepository.existsById(id)){
            throw new IllegalArgumentException("Student not found with Id: " + id);
        }
        studentRepository.deleteById(id);
    }

    @Override
    public StudentDto updateStudent(Long id, AddStudentRequestDto addStudentRequestDto) {
        Student student =  studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Student not found with Id: " + id));
        modelMapper.map(addStudentRequestDto, student);
        student = studentRepository.save(student);
        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public StudentDto updatePartialStudent(Long id, Map<String, Object> updates) {
        Student student =  studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Student not found with Id: " + id));
        updates.forEach((field, value) -> {
            switch(field) {
                case "name": student.setName((String) value);
                break;
                case "email": student.setEmail((String) value);
                break;
                default: throw new IllegalArgumentException("Unknown field: " + field);
            }
        });
        Student savedStudent = studentRepository.save(student);
        return modelMapper.map(savedStudent, StudentDto.class);
    }
}
