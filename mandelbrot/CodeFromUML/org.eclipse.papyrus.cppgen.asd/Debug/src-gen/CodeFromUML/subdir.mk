################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../src-gen/CodeFromUML/CharacterTypes.cpp \
../src-gen/CodeFromUML/Mage.cpp \
../src-gen/CodeFromUML/Warrior.cpp \
../src-gen/CodeFromUML/main.cpp 

OBJS += \
./src-gen/CodeFromUML/CharacterTypes.o \
./src-gen/CodeFromUML/Mage.o \
./src-gen/CodeFromUML/Warrior.o \
./src-gen/CodeFromUML/main.o 

CPP_DEPS += \
./src-gen/CodeFromUML/CharacterTypes.d \
./src-gen/CodeFromUML/Mage.d \
./src-gen/CodeFromUML/Warrior.d \
./src-gen/CodeFromUML/main.d 


# Each subdirectory must supply rules for building sources it contributes
src-gen/CodeFromUML/%.o: ../src-gen/CodeFromUML/%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -I"/home/d3t0x/eclipse-workspace/org.eclipse.papyrus.cppgen.asd/src-gen" -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


