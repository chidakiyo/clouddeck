/// <reference path="d/knockout.d.ts" />

module Model {
	export class Host {
		constructor(name:String){
			this.name(name)
		}
		name = ko.observable()
	}
    export class Hosts {
    	hosts = ko.observableArray()
    }
}

// initializer
class Init {
	static model:Model.Hosts;
	constructor(){
		Init.model = new Model.Hosts();
		Init.model.hosts = ko.observableArray([
			new Model.Host("192.168.1.1"),
			new Model.Host("192.168.1.2")
			])
		ko.applyBindings(Init.model);
	}
}

$(() => new Init());