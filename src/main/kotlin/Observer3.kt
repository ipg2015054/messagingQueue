class Observer3: IObserver {
    override fun notify(message: String) {
        println("Observer3 received message: $message")
    }
}