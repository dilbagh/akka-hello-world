package com.soprabanking.poc.akka.actors;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

/**
 * Hello World Actor.
 */
public class HelloWorld extends AbstractBehavior<HelloWorld.Command> {

    /**
     * Marker interface for all commands this actor can handle.
     */
    interface Command {
    }

    /**
     * Predefined commands.
     */
    public enum PredefinedCommands implements Command {
        SAY_HELLO,
        STOP
    }

    /**
     * Command to change the message of the actor.
     */
    public static class ChangeMessage implements Command {
        private final String newMessage;

        public ChangeMessage(String newMessage) {
            this.newMessage = newMessage;
        }

        public String getNewMessage() {
            return newMessage;
        }
    }

    /**
     * Create a {@link Behavior} instance for this actor.
     *
     * @return Behavior instance
     */
    public static Behavior<Command> create() {
        return Behaviors.setup(HelloWorld::new);
    }

    private String message = "Hello World!";

    private HelloWorld(ActorContext<Command> context) {
        super(context);
    }


    /**
     * Register all the handlers for the messages this actor can receive.
     */
    @Override
    public Receive<Command> createReceive() {
        return newReceiveBuilder()
                .onMessageEquals(PredefinedCommands.SAY_HELLO, this::onSayHello)
                .onMessage(ChangeMessage.class, this::onChangeMessage)
                .onMessageEquals(PredefinedCommands.STOP, this::onStop)
                .build();
    }

    /**
     * Handler for {@link PredefinedCommands#SAY_HELLO} message.
     */
    private Behavior<Command> onSayHello() {
        getContext().getLog().info(message);
        return this; // return self to handle next message
    }

    /**
     * Handler for {@link ChangeMessage} message.
     */
    private Behavior<Command> onChangeMessage(ChangeMessage command) {
        this.message = command.getNewMessage();
        return this; // return self to handle next message
    }

    /**
     * Handler for {@link PredefinedCommands#STOP} message.
     */
    private Behavior<Command> onStop() {
        return Behaviors.stopped();
        // return stopped instance to terminate the actor
    }
}
