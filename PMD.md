# PMD
## _Instalar y ejecutar PMD antes de un git push_

[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://travis-ci.org/joemccann/dillinger)

### Requisitos
- Java 8 o superior
- Java JRE 
- OpenJDK (Preferentemente de Adoptium)
- 7zip
- unzip 
- winrrar 
- https://pmd.github.io/ [1]
- https://github.com/pmd/pmd [2]

### Guia basica de instalacion de PMD 

- Descarga los recursos mediante la url proporcionada
- Desempaqueta los ficheros en una carpeta con una ruta similar a C:\PMD
- Agrega esa ruta C:\PMD\bin en la variable de entorno PATH de tu sistema
- Crea la sigueintes carpetas dentro de PMD \rulesets\java
- El fichero [2] contiene todas las reglas pmd-java 
- Copia los ficheros de pmd-java\src\main\resources\rulesets\java y pegalos en la carpeta de PMD\rulesets\java

### Verifica que la instalacion sea correcta 

- Inicia una terminal y ejecuta PMD, si todo sale bien el comando se debe reconocer.
```sh
C:\User> PMD
Missing required subcommand
Usage: pmd [-hV] [COMMAND]
  -h, --help      Show this help message and exit.
  -V, --version   Print version information and exit.
Commands:
  check     The PMD standard source code analyzer
  cpd       Copy/Paste Detector - find duplicate code
  designer  The PMD visual rule designer
  cpd-gui   GUI for the Copy/Paste Detector
              Warning: May not support the full CPD feature set
  ast-dump  Experimental: dumps the AST of parsing source code
Exit Codes:
  0   Successful analysis, no violations found
  1   An unexpected error occurred during execution
  2   Usage error, please refer to the command help
  4   Successful analysis, at least 1 violation found
```
### Configurar el archivo pre-push
Dentro de la carpeta del proyecto pertinente en .git contiene los ficheros .sample, se realizara la configuracion de pre-push eliminando su terminacion, la configuracion es la siguiente 
```sh
remote="$1"
url="$2"

zero=$(git hash-object --stdin </dev/null | tr '[0-9a-f]' '0')

while read local_ref local_oid remote_ref remote_oid
do
	if test "$local_oid" = "$zero"
	then
		# Handle delete
		:
	else
		if test "$remote_oid" = "$zero"
		then
			# New branch, examine all commits
			range="$local_oid"
		else
			# Update to existing branch, examine new commits
			range="$remote_oid..$local_oid"
		fi

		# Check for WIP commit
		commit=$(git rev-list -n 1 --grep '^WIP' "$range")
		if test -n "$commit"
		then
			echo >&2 "Found WIP commit in $local_ref, not pushing"
			exit 1
		fi

		# Run tests with PMD
		pmd_command="pmd check -d src/main/java -f xml -R C:/PMD/rulesets/java/gardian.xml"
		pmd_output=$(eval "$pmd_command")

		# Check PMD output for issues
		if echo "$pmd_output" | grep -q "ParseException"; then
			echo >&2 -e "\e[1;31mFAILED\e[0m PMD found problems in their code. Aborting push, correct the changes before performing the next push"
			echo "$pmd_output"
			exit 1
		else
			echo >&2 -e "\e[1;34mSUCCESS\e[0m PMD not found problems \e[1;32mpush in process\e[0m"
			exit 0
		fi
	fi
done

exit 1
```

Por ultimo se debe agregar el archivo gardian.xml el cual contiene la siguiente estructura. 

```sh
<?xml version="1.0"?>
<ruleset name="Java Code Rules" xmlns="http://pmd.sourceforge.net/ruleset/2.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://pmd.sourceforge.net/ruleset/2.0.0 http://pmd.sourceforge.net/ruleset_2_0_0.xsd">

    <description>Java code rules</description>

    <rule ref='rulesets/java/strings.xml'/>

</ruleset>
```

### Revisar la implementacion
Por ultimo, el commit que realices el archivo gardian revisara la implementacion del codigo "simple", si el test pasa, marcara un mensaje indicando que se debe modificar el archivo gardian, pero mostrara el siguiente mensaje. 
```sh
SUCCESS PMD not found problems in your code, push in process
```

Si el test falla, el mensaje sera el siguiente. 
```sh
FAILED PMD found problems in their code. Aborting push, correct the changes before performing the next push
```