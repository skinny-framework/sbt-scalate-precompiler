#!/bin/bash
sbt ++2.12.0 \
    clean \
    precompiler/publishSigned \
    ++2.11.8 \
    clean \
    precompiler/publishSigned \
    ++2.10.6 \
    clean \
    precompiler/publishSigned \
    ++2.10.6 \
    clean \
    plugin/publishSigned
