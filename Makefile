JA = java
JC = javac
RM = rm

MAKEFILE_PATH := $(abspath $(lastword $(MAKEFILE_LIST)))
MAKEFILE_DIR := $(dir $(MAKEFILE_PATH)) 

PRJ = myCode
PKG_1 = src
PKG_2 = game

default:
	$(JC) -d $(PRJ)/bin $(PRJ)/*.java $(PRJ)/$(PKJ_1)/*.java $(PRJ)/$(PKG_2)/*.java
	
#compile only the src package
src:
	$(JC) $(PRJ)/$(PKG_1)/*.java
	
#compile only the added package
added:
	$(JC) $(PRJ)/$(PKG_2)/*.java
	
#this to launch the Main, which is the game
exec:
	$(JA) -cp $(PRJ)/bin $(PRJ).Main
	
#this to print the path of the MakeFile (because at the beginning I had problems)
printPath:
	@echo Directory of MakeFile: $(MAKEFILE_DIR)