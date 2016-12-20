#include <jni.h>
#include <string>

/**
  检测是否为模拟器
 */
void checkEmulator() {

}

/**
  检测是否为debug调试模式
 */

void checkDebuggabe() {

}

/**
  使用ptrace模式
 */
void userPtrace() {

}


extern "C"
jstring
Java_mixi_com_woodenhorsedemo_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

