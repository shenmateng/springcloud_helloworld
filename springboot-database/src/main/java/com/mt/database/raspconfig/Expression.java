package com.mt.database.raspconfig;

import lombok.Data;

/**
 * @Author: mateng
 * @program: spring-cloud
 * @Date: 2024/9/12 14:55
 * @Version:
 * @Description:
 * ░░░░░░░░░░░░░░░░░░░░░░░░▄░░
 * ░░░░░░░░░▐█░░░░░░░░░░░▄▀▒▌░
 * ░░░░░░░░▐▀▒█░░░░░░░░▄▀▒▒▒▐
 * ░░░░░░░▐▄▀▒▒▀▀▀▀▄▄▄▀▒▒▒▒▒▐
 * ░░░░░▄▄▀▒░▒▒▒▒▒▒▒▒▒█▒▒▄█▒▐
 * ░░░▄▀▒▒▒░░░▒▒▒░░░▒▒▒▀██▀▒▌
 * ░░▐▒▒▒▄▄▒▒▒▒░░░▒▒▒▒▒▒▒▀▄▒▒
 * ░░▌░░▌█▀▒▒▒▒▒▄▀█▄▒▒▒▒▒▒▒█▒▐
 * ░▐░░░▒▒▒▒▒▒▒▒▌██▀▒▒░░░▒▒▒▀▄
 * ░▌░▒▄██▄▒▒▒▒▒▒▒▒▒░░░░░░▒▒▒▒
 * ▀▒▀▐▄█▄█▌▄░▀▒▒░░░░░░░░░░▒▒▒
 * You are not expected to understand this
 */
@Data
public class Expression {

    private RaspSwitchsStatus bcel;

    private RaspSwitchsStatus freemarker;

    private String desc;

    private RaspSwitchsStatus groovy;

    private RaspSwitchsStatus javax;

    private RaspSwitchsStatus jexl3;

    private RaspSwitchsStatus jxpath;

    private RaspSwitchsStatus mvel;

    private RaspSwitchsStatus ognl;

    private RaspSwitchsStatus script;

    private RaspSwitchsStatus spel;

    private RaspSwitchsStatus velocity;
}
