package com.semka.pets.pass_gen_v2.commands;

import com.semka.pets.pass_gen_v2.rulesprovider.RulesProvider;
import com.semka.pets.pass_gen_v2.util.PathUtil;
import lombok.RequiredArgsConstructor;
import org.passay.CharacterRule;
import org.passay.PasswordGenerator;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Command(group = "Main", description = "Главная группа команд")
@RequiredArgsConstructor
public class CreatePassword {

    private final RulesProvider rulesProvider;
    private final PasswordGenerator passwordGenerator;

    @Command(description = "Эта команда создает пароли и при необходимости может сохранить их в файл.")
    public String create(
            @Option(
                    longNames = "count",
                    shortNames = 'c',
                    description = "Желаемое количество генерируемых паролей.",
                    defaultValue = "10") int passwordsCount,
            @Option(
                    longNames = "length",
                    shortNames = 'l',
                    description = "Длина генерируемых паролей.",
                    defaultValue = "8") int passwordLength,
            @Option(
                    longNames = "withSpecial",
                    shortNames = 's',
                    description = "Нужно ли использовать спец-символы при генерации паролей.",
                    defaultValue = "false") boolean useSpecialCharacters,
            @Option(
                    longNames = "saveInFile",
                    shortNames = 'f',
                    description = "Нужно ли сохранить созданные пароли в файл",
                    defaultValue = "false") boolean needSaveInFile
            //добавить интерактиное общение для возможности указать имя файла с паролями
    ) {
        List<CharacterRule> rules;
        if (useSpecialCharacters) {
            rules = rulesProvider.getAllRules();
        } else {
            rules = rulesProvider.getRulesWithOutSpecial();
        }
        StringBuilder passwords = new StringBuilder();

        for (int i = 1; i <= passwordsCount; i++) {
            String password = passwordGenerator.generatePassword(passwordLength, rules);
            passwords.append(String.format("%3d.\t%s\n", i, password));
        }

        String passwordsString = passwords.toString();
        if (needSaveInFile) {
            String pathExecuted = PathUtil.getPathExecuted(CreatePassword.class);
            try (FileWriter writer = new FileWriter(pathExecuted + "/passwords.txt")) {
                writer.write(passwordsString);
            } catch (IOException e) {
                //корректный обработчик ошибок https://habr.com/ru/companies/spring_aio/articles/903864/
                System.err.println("Error writing to file: " + e.getMessage());
            }
        }
        return passwordsString;
    }
}
