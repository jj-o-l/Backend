package com.example.javaproject.Table;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
public class Community {
    @Id
                                                                          Integer id;
    Integer UserId;
    Integer parentId;
    String comment;
}
