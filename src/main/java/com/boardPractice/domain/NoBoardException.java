package com.boardPractice.domain;

public class NoBoardException extends RuntimeException{
    public NoBoardException(){}
    public NoBoardException(String message){
        super(message);
    }
}
