# Copyright (C) 2009 The Android Open Source Project
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#
LOCAL_PATH:= $(call my-dir)

include $(CLEAR_VARS)

USE_OPENGL_ES_1_1 := true

ifeq ($(USE_OPENGL_ES_1_1), true)
    OPENGLES_LIB  := -lGLESv1_CM
	OPENGLES_DEF  := -DUSE_OPENGL_ES_1_1
else
    OPENGLES_LIB  := -lGLESv2
	OPENGLES_DEF  := -DUSE_OPENGL_ES_2_0
endif


LOCAL_MODULE    := libgl2jni
#LOCAL_CFLAGS    := -Werror
LOCAL_SRC_FILES := md2.cpp texturing.cpp
LOCAL_LDLIBS    := -llog $(OPENGLES_LIB)
LOCAL_CPPFLAGS   += -std=c++0x -D__STDC_INT64__
TARGET_PLATFORM := android-14

include $(BUILD_SHARED_LIBRARY)
