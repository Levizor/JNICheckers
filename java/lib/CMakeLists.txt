cmake_minimum_required(VERSION 3.29)
project(lib)

set(CMAKE_CXX_STANDARD 26)

add_library(lib SHARED library.cpp)
