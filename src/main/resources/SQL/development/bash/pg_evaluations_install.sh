#!/bin/bash
# Get script directory
SOURCE="${BASH_SOURCE[0]}"
while [ -h "$SOURCE" ]; do
	DIR="$( cd -P "$( dirname "$SOURCE" )" && pwd )"
	SOURCE="$( readlink "$SOURCE" )"
	[[ $SOURCE != /* ]] && SOURCE="$DIR/$SOURCE"
done
SQL_DIR="$( cd -P "$( dirname "$SOURCE" )" && pwd )/sql"

# Confirm postgres is on path else prompt
function prompt() {
    read PG_PATH
    PG_PATH="${PG_PATH%\"}"
    PG_PATH="${PG_PATH#\"}"
    PG_PATH="${PG_PATH%\'}"
    PG_PATH="${PG_PATH#\'}"
    export PATH=$PATH:$PG_PATH
}

command -v psql || {
    OLD_PATH=$PATH
    echo "Enter location of postgres binaries: "
    prompt
    # Check input until valid
    while ! [[ $(command -v "$PG_PATH/psql") ]]
    do
        PATH=$OLD_PATH
        echo "Invalid location. Enter location of postgres binaries (e.g. postgres, psql): "
        prompt
    done
    export PATH
}

# Get postgres admin login info
echo -n "Enter postgres Admin Username: "
read PGUSER
export PGUSER

echo -n "Enter password: "
read -s PGPASSWORD
export PGPASSWORD
echo

# Drop evaluations database if exists, then create and fill
psql -d evaluations -a -f $SQL_DIR/pg_kill_connections.sql
dropdb --if-exists evaluations
psql -d postgres -a -f $SQL_DIR/pg_create_user.sql
createdb -O evaluations evaluations
psql -d evaluations -a -f $SQL_DIR/pg_create_tables.sql
psql -d evaluations -a -f $SQL_DIR/pg_fill_tables.sql

# Display connection info to user
echo
echo ----- Connection Information: -----
echo
echo USERNAME: evaluations
echo PASSWORD: p4ssw0rd
echo JDBC URL: jdbc:postgresql://localhost:5432/evaluations
echo

# Set environment variables if desired
while true; do
    read -p "Set environment variables for database connection? (y/n): " yn
    case $yn in
        [Yy]* )
            touch ~/.bash_profile
			sed -i'.bak' '/INTEVAL/d' ~/.bash_profile 
			sed -i'.bak' '/Evaluations Database environment variables/d' ~/.bash_profile
			export INTEVAL_USER=evaluations
			export INTEVAL_PASS=p4ssw0rd
			export INTEVAL_URL=jdbc:postgresql://localhost:5432/evaluations
			printf "\n#Evaluations Database environment variables\nexport INTEVAL_USER=evaluations\nexport INTEVAL_PASS=p4ssw0rd\nexport INTEVAL_URL=jdbc:postgresql://localhost:5432/evaluations\n" >> ~/.bash_profile
			break;;
        [Nn]* ) exit;;
        * ) echo "Please answer yes or no.";;
    esac
done
