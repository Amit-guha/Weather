#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_mindorks_myapplication_APIKeyLibrary_getAPIKey(JNIEnv* env, jobject /* this */) {
    std::string api_key = "69c6aa61a0f3e87516a4192d1666ebdd";
    return env->NewStringUTF(api_key.c_str());
}