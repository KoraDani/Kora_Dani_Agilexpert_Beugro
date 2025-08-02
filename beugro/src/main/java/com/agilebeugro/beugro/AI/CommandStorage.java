package com.agilebeugro.beugro.AI;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommandStorage {
    private String command;
    private String[] arguments;

}
