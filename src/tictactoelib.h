/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class tictactoelib */

#ifndef _Included_tictactoelib
#define _Included_tictactoelib
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     tictactoelib
 * Method:    initializeBoard
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_tictactoelib_initializeBoard
  (JNIEnv *, jobject);

/*
 * Class:     tictactoelib
 * Method:    makeMove
 * Signature: (IIC)Z
 */
JNIEXPORT jboolean JNICALL Java_tictactoelib_makeMove
  (JNIEnv *, jobject, jint, jint, jchar);

/*
 * Class:     tictactoelib
 * Method:    checkWin
 * Signature: ()C
 */
JNIEXPORT jchar JNICALL Java_tictactoelib_checkWin
  (JNIEnv *, jobject);

/*
 * Class:     tictactoelib
 * Method:    resetBoard
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_tictactoelib_resetBoard
  (JNIEnv *, jobject);

/*
 * Class:     tictactoelib
 * Method:    getBoard
 * Signature: ()[[C
 */
JNIEXPORT jobjectArray JNICALL Java_tictactoelib_getBoard
  (JNIEnv *, jobject);

#ifdef __cplusplus
}
#endif
#endif
