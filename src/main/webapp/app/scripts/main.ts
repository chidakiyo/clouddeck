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

module Keys {
	export class URL {
		static hosts = "/mock/host.json"
	}
}

class Net {
	static connect(url:String) : String {
		var handle = alert(json:String) {alert(json)}
		$.getJSON(url, handle(json))
		return ""
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
		Net.connect(Keys.URL.hosts);
	}
}


$(() => new Init());