//
// Created by mixi on 2016/12/23.
//
#include "util.h"


bool check_qemu() {
    //模拟器特定文件
    bool is_qemu = false;
    is_qemu |= file_exites("/system/lib/libc_malloc_debug_qemu.so");
    is_qemu |= file_exites("/sys/qemu_trace");
    is_qemu |= file_exites("/system/bin/qemu-props");
    return is_qemu;
}

bool use_debug_mode() {
    //Linux进程状态文件利用TracerPid[40]记录调试（跟踪）进程的进程号，
    // 如果进程未被调试（跟踪）则该值为0。Android继承了Linux系统的这种特性，
    // 所以通过读取进程的/proc/self/status文
    FILE *statusFile;
    int tracePidValue;
    char tpid[512];
    statusFile = fopen("/proc/self/status", "r");
    while (fgets(tpid, 512, statusFile)) {
        if (!strncmp(tpid, "TracerPid:", 10)) {
            sscanf(tpid, "TracerPid: %d", &tracePidValue);
            break;
        }
    }
    fclose(statusFile);
    if (tracePidValue) {
        pid_t this_pid = getpid();
        kill(this_pid, SIGKILL);
    }
    return false;

}

bool use_ptrace() {
    return true;
}
