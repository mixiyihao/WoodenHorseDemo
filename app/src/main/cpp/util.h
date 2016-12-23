//
// Created by mixi on 2016/12/23.
//
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <android/log.h>

#ifndef WOODENHORSEDEMO_UTIL_H
#define WOODENHORSEDEMO_UTIL_H


#define LOGTAG "mixi"
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, LOGTAG, __VA_ARGS__)


static bool file_exites(const char *path) {
    int result = access(path, 0);
    return result < 0 ? false : true;

}


/**
 *
 * 检测模拟器
 */
bool check_qemu();

/**
 * 检测是否用debug模式
 */
bool use_debug_mode();

/**
 * 使用ptrace防止dump
 */
bool use_ptrace();


#endif //WOODENHORSEDEMO_UTIL_H
