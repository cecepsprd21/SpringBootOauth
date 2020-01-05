package com.jan.testingoauth.response;

import com.jan.testingoauth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class ResponseDao<T extends Object> {
    private int code;
    private String status;
    private String message;
    private T data;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity List(ResponseDao responseDao){
        setCode(200);
        setStatus("OK");
        setMessage("SUCCESS");
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(responseDao);
    }

    public ResponseEntity OK(ResponseDao responseDao){
        setCode(200);
        setStatus("OK");
        setMessage("SUCCESS");
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(responseDao);
    }
    public ResponseEntity notFound(){
        setCode(404);
        setStatus("NOT FOUND");
        setMessage("data not found");
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(null);
    }

    public ResponseEntity created(ResponseDao responseDao){
        setCode(201);
        setStatus("CREATED");
        setMessage("created successfully");
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(responseDao);
    }

    public ResponseEntity updated(ResponseDao responseDao){
        setCode(201);
        setStatus("UPDATED");
        setMessage("Data successfully updated");
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseDao);
    }

    public ResponseEntity deleted(ResponseDao responseDao){
        setCode(201);
        setStatus("delete");
        setMessage("Data successfully deleted");
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseDao);
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}