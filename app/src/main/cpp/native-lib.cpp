#include <jni.h>
#include <string>
#include "util.h"





extern "C"
jstring
Java_mixi_com_woodenhorsedemo_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    bool result = check_qemu();
    LOGD("the check qemu_ %d", result);
    bool result1 = use_debug_mode();
    LOGD("the use debug mode =  %d", result1);
    return env->NewStringUTF(hello.c_str());
}

