package com.agilebeugro.beugro.AI;

import com.agilebeugro.beugro.commands.ApplicationCommand;
import com.agilebeugro.beugro.commands.BackGroundCommand;
import com.agilebeugro.beugro.commands.GroupCommand;
import com.agilebeugro.beugro.commands.UserCommand;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ShellComponentCollector {

    @Bean
    public List<Object> shellComponents(ApplicationCommand applicationCommand, BackGroundCommand backGroundCommand, GroupCommand groupCommand, UserCommand userCommand) {
        return List.of(applicationCommand,backGroundCommand, groupCommand, userCommand);
    }
}
