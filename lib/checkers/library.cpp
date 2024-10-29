#include "library.h"

#include <array>
#include <iostream>

#include "Board.h"

Board board;

JNIEXPORT JNICALL jint Java_main_Main_makeMove(JNIEnv * env, jclass, jintArray from, jintArray to) {
    int* a = env->GetIntArrayElements(from, 0);
    std::array<int, 2> fromp = {a[0], a[1]};

    a = env->GetIntArrayElements(to, 0);

    std::array<int, 2> top = {a[0], a[1]};

    jint result = board.makeMove(fromp, top);
    return result;
}

JNIEXPORT JNICALL jint Java_main_Main_getTile(JNIEnv * env, jclass, jintArray array) {
    int * c = env->GetIntArrayElements(array, NULL);
    jint tile = board.table[c[0]][c[1]];
    return tile;
}


jobjectArray convertToJObjectArray(JNIEnv* env, const std::vector<std::array<int, 2>>& vector) {
    jclass intArrayClass = env->FindClass("[I"); // Class reference for jintArray
    jobjectArray jobjectArray = env->NewObjectArray(vector.size(), intArrayClass, nullptr);


    for (int i = 0; i < vector.size(); ++i) {
        const std::array<int, 2>& arr = vector[i];

        jintArray jintArray = env->NewIntArray(2);

        env->SetIntArrayRegion(jintArray, 0, 2, arr.data());

        env->SetObjectArrayElement(jobjectArray, i, jintArray);

        env->DeleteLocalRef(jintArray);
    }

    return jobjectArray;
}
JNIEXPORT jobjectArray JNICALL Java_main_Main_getPossibleMoves
  (JNIEnv * env, jclass, jintArray from) {
    int* arr = env->GetIntArrayElements(from, NULL);
    std::array<int, 2> arr2 = {arr[0], arr[1]};
    return convertToJObjectArray(env, board.getPossibleActions(arr2));
}
JNIEXPORT void JNICALL Java_main_Main_restartGame
  (JNIEnv *, jclass) {
    board = Board();
}
