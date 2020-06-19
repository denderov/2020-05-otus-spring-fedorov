package ru.otus.homework.quiz.domain;

import java.util.List;
import lombok.Value;

@Value
public class TestRoom {

  String firstName;
  String lastName;
  List<TestQuestion> testQuestions;

}