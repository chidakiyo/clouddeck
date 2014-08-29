/// <reference path="d/knockout.d.ts" />

module Model {
	export class EHost {
		constructor(name:String){
			this.name(name)
		}
		name = ko.observable()
	}
	export class CHost {
		constructor(name:String, power:boolean){
			this.name(name)
			this.power(power)
		}
		name = ko.observable()
		power = ko.observable()
	}

	export class Vmodel {
		esxhosts = ko.observableArray()

		svname = ko.observable()
		clienthosts = ko.observableArray()
	}
}

// initializer
class Init {
	static model:Model.Vmodel
	constructor(){
		Init.model = new Model.Vmodel();
		Init.model.esxhosts = ko.observableArray([
			new Model.EHost("192.168.1.1"),
			new Model.EHost("192.168.1.2")
			])

		Init.model.svname("192.168.1.1")
		Init.model.clienthosts = ko.observableArray([
			new Model.CHost("hoge", false),
			new Model.CHost("foo", true)
			])

		ko.applyBindings(Init.model);
	}
}


$(() => new Init());