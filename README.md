Drools Shell
============
The aim of this project is to provide a simple command line interface for test rules allowing you to insert, modify and retract facts. You also have commands to to query the state of the stateful knowledge session running behind the shell.

Running
-------
At the moment there is no stable release, so to build the tarball you should do it from source.
    $ git clone git://github.com/diega/drools-shell.git
    $ cd drools-shell
    $ mvn assembly:assembly
After running this commands you will get a .tar.gz file into the target/ folder.
Extract that file into your desired destination and run:
    $ ./drools.sh
It'll open a command prompt ready to call any of the drools-shell commands
    drools>
For information about supported arguments run:
    $ ./drools.sh --help 

### Microsoft Windows users
Drools Shell should be able to run into Microsoft Windows systems but it has never been tested (running drools.bat instead of drools.sh)

Commands
--------
The following commands are supported:

* lsrules
* lsfacts
* insert
* retract
* fire

For information about arguments call each command with the `--help` argument inside the shell.

Hacking
-------
All the configuration is Spring based, so should be easy for anyone to understand how different layers are organized. In src/main/resources/context.xml you will find every command and which class implements its behavior.
The most relevant interfaces are:

* org.plugtree.drools.shell.commands.CliCommand
* org.plugtree.drools.shell.outputbuilders.OutputBuilder<T>

For development purposes you can run the shell standing into the root of the project codebase with:
     mvn exec:java -Dexec.args="--help"
You can pass to -Dexec.args any argument you want, --help here is for the sake of the example.

Take a look into the `TODO` file to get an idea from where to begin if you are willing to help.
