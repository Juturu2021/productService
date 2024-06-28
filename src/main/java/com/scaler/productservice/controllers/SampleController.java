    package com.scaler.productservice.controllers;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.PathVariable;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RestController;

    @RestController
    @RequestMapping("/say")
    public class SampleController {
        @GetMapping("/hello/{name}/{count}")
        public String sayHello(@PathVariable("name") String student_name,
                               @PathVariable("count") int count){
            String output = "";
            for(int i=0; i<count; i++){
                output = output + "hello " + student_name + "\n";
            }
            return output;
        }
        @GetMapping("/hello") // Sets an address for method where data is read
        public String sayHello()
        {
            return "Hello";

        }

        @GetMapping("bye")
        public String sayBye(){
            return "Bye Everyone!";
        }
    }
