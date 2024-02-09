package com.example.atipera.model;

import lombok.*;

@Getter
@Builder
public class Branch {
    private String name;
    private Commit commit;
}
