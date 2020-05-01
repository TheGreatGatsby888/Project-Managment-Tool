package com.project.ppmt.web;

import com.project.ppmt.domain.Project;
import com.project.ppmt.services.MapValidatonError;
import com.project.ppmt.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private MapValidatonError mapValidatonError;

    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result) {
        ResponseEntity<?> errorMap = mapValidatonError.getErrorMap(result);
        if (errorMap != null) return errorMap;
        
        Project p = projectService.saveOrUpdateProject(project);
        return new ResponseEntity<>(p, HttpStatus.CREATED);
    }
}
