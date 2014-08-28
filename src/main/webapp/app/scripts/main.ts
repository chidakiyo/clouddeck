module Sayings {
    export class Greeter {
        greeting: string;
        constructor(message: string) {
            this.greeting = message;
        }
        greet() {
            return 'Allo, ' + this.greeting + '!';
        }
    }
}

var greeter = new Sayings.Greeter('TypeScript');
console.log(greeter.greet());
