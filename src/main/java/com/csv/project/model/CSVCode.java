package com.csv.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.lang.Nullable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CSVCode {
	
	@Id
	@NonNull
    private String code;
    @Nullable
    private String source;
    @Nullable
    private String codeListCode;
    @Nullable
    private String displayValue;
    @Nullable
    private String longDescription;
    @Nullable
    private String fromDate;
	@Nullable
    private String toDate;
    @Nullable
    private String sortingPriority;  
}
