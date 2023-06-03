package edu.example.demoproject.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class PageDto<T>{
    private List<T> items;
    private int page;
    private Long total;
    private int size;
}
