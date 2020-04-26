package com.soprabanking.poc.akka.actors;

import akka.actor.typed.ActorSystem;

public class HelloWorldApp {

    public static void main(String[] args) {
        ActorSystem<HelloWorld.Command> mySystem =
                ActorSystem.create(HelloWorld.create(), "MySystem");

        mySystem.tell(HelloWorld.PredefinedCommands.SAY_HELLO);

        mySystem.tell(new HelloWorld.ChangeMessage("Hello Actor!"));
        mySystem.tell(HelloWorld.PredefinedCommands.SAY_HELLO);

        mySystem.tell(new HelloWorld.ChangeMessage("Goodbye Actor!"));
        mySystem.tell(HelloWorld.PredefinedCommands.SAY_HELLO);

        mySystem.tell(HelloWorld.PredefinedCommands.STOP);
    }
}
