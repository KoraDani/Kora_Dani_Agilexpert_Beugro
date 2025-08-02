package com.agilebeugro.beugro.AI;

import com.agilebeugro.beugro.services.ApplicationService;
import com.agilebeugro.beugro.services.BackGroundService;
import com.agilebeugro.beugro.services.GroupService;
import com.agilebeugro.beugro.services.UserService;
import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.core.JsonValue;

import com.openai.models.ChatCompletion;
import com.openai.models.ChatCompletionCreateParams;
import com.openai.models.ChatCompletionMessage;
import com.openai.models.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class OpenAIService {

    private final UserService userService;
    private final GroupService groupService;
    private final BackGroundService backGroundService;
    private final ApplicationService applicationService;

    @Autowired
    public OpenAIService(UserService userService, GroupService groupService, BackGroundService backGroundService, ApplicationService applicationService) {
        this.userService = userService;
        this.groupService = groupService;
        this.backGroundService = backGroundService;
        this.applicationService = applicationService;
    }

    OpenAIClient client = OpenAIOkHttpClient.builder().apiKey("sk-").build();

    public String prompt(String input) {


        ChatCompletionCreateParams params = ChatCompletionCreateParams.builder()
                .temperature(0.0)
                .addMessage(ChatCompletionMessage.builder()
                        .role(JsonValue.from("assistant"))
                        .content("""
                                You are a CLI assistant.
                                Convert natural language instructions into a shell command.
                                Respond ONLY with the command and parameters. No explanation.
                                There are 4 table in the database: User, Application, Background, Group
                                Below you see how the tables are look like and see what data is in the tables:
                                User table:
                                    This is how you will receve all users form the db: User(userId=<number>, username=<string>, backGround=<BackGround>, application=<Application>, groups=<Group>)
                                    If the user tells you to generate random users do not use existing usernames, you can see the existing user below:
                                """ +
                                this.userService.getAllUsersForAI()
                                +
                                """
                                        BackGround table:
                                            This is how you will receve all users form the db: "BackGround{backGroundId=<number>, backGroundName=<string>}
                                            If the user tells you to generate random background do not use existing beackground name, you can see the existing backgrounds below:
                                        """ +
                                this.backGroundService.getAllBackGroundForAI()
                                +
                                """
                                        Application table:
                                            This is how you will receve all users form the db: "Application{applicationId=<number>, applicationName=<string>}
                                            If the user tells you to generate random application do not use existing Application name, you can see the existing Application below:
                                        """ +
                                this.applicationService.getAllApplicationForAI()
                                +
                                """
                                        Group table:
                                            This is how you will receve all users form the db: "Group{groupId=<number>, groupName=<string>, appearance=<string> }
                                            If the user tells you to generate random group do not use existing group name, you can see the existing group below:
                                        """ +
                                this.groupService.listAllGroupForAI()
                                +

                                """
                                
                                The order of data creation, it is important that these are done in this order, for all 4 tables, the data for each table must first be created, and only then can it be linked to another table: create -> add to object
                                Please when generating the output do not use backslash + n
                                Please when generating the output the generaton regex method: arg arg; and please take note the som method only has one argument

                                Examples:
                                        Input: Add Bela to the users
                                        Output: userAdd: Bela;
                                        
                                        Input: Update User from Bela to Balazs "
                                        Output: userUpdate: Bela Balazs;
                                        
                                        Input:Delete Bela from the Database "
                                        Output: userRemove: Bel;
                                        
                                        Input: List all existing users "
                                        Output: listAllUser;
                                        
                                        Input: List all information about Bela
                                        Output: listAllInfoAboutUser: Bela;
                                        
                                        Input:Add a new application to the database
                                        Output: addIcon: GPS;
                                        
                                        Input: Update an application 
                                        Output: updateIcon: GPS GPs;
                                        
                                        Input: Delete an application
                                        Output: removeIcon: GPS;
                                        
                                        Input: Install an application for a user
                                        Output: installApp: GPS;
                                        
                                        Input: Remove an application from a user
                                        Output: deleteAppFromUser: GPS;
                                        
                                        Input: Start an application
                                        Output: startApplication: GPS;
                                        
                                        Input: Close an application
                                        Output: closeApplication: GPS;
                                        
                                        Input: List all running applications
                                        Output: runningApplications;
                                        
                                        Input: List all applications
                                        Output: listAllApplications;
                                        
                                        Input: Add new Background to the application
                                        Output: addBackGround: ocean;
                                        
                                        Input: Update background
                                        Output: updateBackGround: ocean OCEAN;
                                        
                                        Input: Delete background
                                        Output: removeBackGround: ocean;
                                        
                                        Input: Select background for user
                                        Output: selectBackGround: ocean;
                                        
                                        Input: List all avialable backgrounds
                                        Output: listAllBackGround;
                                        
                                        Input: Create a group
                                        Output: createGroup: Family;
                                        
                                        Input: Update Group for Family to FAMILY
                                        Output: updateGroup: Family FAMILY;
                                        
                                        Input: Add user to the Family group
                                        Output: addUserGroup: GroupName Username;
                                        
                                        Input: Remove user from the Family group
                                        Output: removeUserFromGroup: GroupName Username;
                                        
                                        Input: Delete family group
                                        Output: deleteGroup: GroupName;
                                        
                                        Input: Change Group appearance
                                        Output: changeAppearance: GroupName Appearance;
                                        
                                        Input: Delete appearance form the group
                                        Output: deleteAppearance: GroupName;
                                        
                                        Input: List all group
                                        Output: listAllGroup;
                                        
                                        Input: List all table from the database
                                        Output: listAllUser; listAllBackground; listAllApplications; listAllGroup;
                                        
                                        """)
                        .refusal("")
                        .build())
                .addMessage(ChatCompletionMessage.builder()
                        .role(JsonValue.from("user"))
                        .content(input)
                        .refusal("")
                        .build())
                .model(ChatModel.GPT_4O_MINI)
                .build();


//        System.out.println(params.toString());

        ChatCompletion chatCompletion = client.chat().completions().create(params);
//        System.out.println(chatCompletion.choices().get(0).message().content());
        return chatCompletion.choices().get(0).message().content().toString().replace("Optional[", "").replace("]", "");
//        return null;
    }


}
