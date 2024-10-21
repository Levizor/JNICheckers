cmake_minimum_required(VERSION 3.29)
project(checkers)

set(CMAKE_CXX_STANDARD 26)

add_library(checkers SHARED library.h library.cpp
        Board.cpp
        Board.h)

target_include_directories(checkers PRIVATE "/home/levizor/.jdks/openjdk-23/include")

target_include_directories(checkers PRIVATE "/home/levizor/.jdks/openjdk-23/include/linux")