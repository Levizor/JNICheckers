#include "library.h"

#include <iostream>

#include "Board.h"

Board board;

JNIEXPORT JNICALL jboolean Java_main_Main_makeMove(JNIEnv * env, jclass, jobjectArray array) {
    int arr [2][2];
    for (int i = 0; i < 2; ++i) {
        jintArray row = (jintArray) env->GetObjectArrayElement(array, i);
        jint* rowElements = env->GetIntArrayElements(row, nullptr);

        for (int j = 0; j < 2; ++j) {
            arr[i][j] = rowElements[j];
        }

        env->ReleaseIntArrayElements(row, rowElements, JNI_ABORT);
        env->DeleteLocalRef(row);
    }

    jboolean result = board.makeMove(arr[0], arr[1]);
    return result;
}

JNIEXPORT JNICALL jobjectArray Java_main_Main_getTable(JNIEnv * env, jclass) {
    auto &table = board.table;
    jclass intArrayClass = env->FindClass("[I");
    jobjectArray resultArray = env->NewObjectArray(8, intArrayClass, nullptr);

    for (int i = 0; i < 8; ++i) {
        jintArray intArray = env->NewIntArray(8);

        env->SetIntArrayRegion(intArray, 0, 8, table[i]);
        env->SetObjectArrayElement(resultArray, i, intArray);
        env->SetObjectArrayElement(resultArray, i, intArray);

        env->DeleteLocalRef(intArray);
    }

    return resultArray;

}
