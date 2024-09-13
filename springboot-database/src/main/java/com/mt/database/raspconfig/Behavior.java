/*
 * Copyright (c) 2020-2030 Sishun.Co.Ltd. All Rights Reserved.
 */

package com.mt.database.raspconfig;

import lombok.Data;
import java.io.Serializable;


/**
 * @version ：M1.0
 * @program ：bmp-prm
 * @date ：Created in 2021/04/14 16:58
 */
@Data
public class Behavior implements Serializable {
    private static final long serialVersionUID = -15469004241082130L;

    private CommandExec command_exec;

    private Connect connect;

    private Dns dns;

    private Expression expression;

    private FileCopy file_copy;

    private FileDelete file_delete;

    private FileMove file_move;

    private FileRead file_read;

    private FileReadWrite file_read_write;

    private FileUpload file_upload;

    private FileWrite file_write;

    private Jndi jndi;

    private Jni jni;

    private Jrmp jrmp;

    private ListFile list_file;

    private Memshell memshell;

    private Ognls ognl;

    private Reflect reflect;

    private Sqli sqli;

    private Ssrf ssrf;

    private Thread thread;

    private Traversal traversal;

    private Xpathi xpathi;

    private Xxe xxe;

}