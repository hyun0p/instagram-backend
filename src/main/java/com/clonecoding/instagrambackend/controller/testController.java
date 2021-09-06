package com.clonecoding.instagrambackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {

    @GetMapping("tt")
    public String tt() {
        return "Hello world!";
    }

    @GetMapping("test")
    public TestObject test(@RequestParam("text") String text) {
        TestObject test = new TestObject();
        test.setText(text);
        return test;
    }

    static class TestObject {
        private String text;

        public String getText() {
            return text;
        }
        public void setText(String text) {
            this.text = text;
        }
    }
}
