package com.semka.pets.pass_gen_v2.rulesprovider;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.passay.CharacterRule;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RulesProvider {
    private final CharacterRule lowerCaseRule;
    private final CharacterRule upperCaseRule;
    private final CharacterRule digitRule;
    private final CharacterRule splCharRule;

    public List<CharacterRule> getAllRules() {
        return List.of(lowerCaseRule, upperCaseRule, digitRule, splCharRule);
    }

    public List<CharacterRule> getRulesWithOutSpecial() {
        return List.of(lowerCaseRule, upperCaseRule, digitRule);
    }
}
