package com.example.atipera.model;

import java.util.List;

public record RecordRepoInfo(String name, RecordOwner owner, List<RecordBranch> branch) {
}
