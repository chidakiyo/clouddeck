/// <reference path="d/jquery.d.ts" />
/// <reference path="d/knockout.d.ts" />
/// <reference path="d/underscore.d.ts" />

module Model {
	export class EHost {
		constructor(name:string){
			this.name(name)
		}
		name = ko.observable()
		getChildren(){
			Net.guest(Keys.URL.guests + this.name());
		}
	}
	export class CHost {
		constructor(name:string, power:boolean, full:string){
			this.name(name)
			this.power(power)
			this.full(full)
		}
		name = ko.observable()
		power = ko.observable()
		full = ko.observable()
	}

	export class Vmodel {
		esxhosts = ko.observableArray()

		svname = ko.observable()
		clienthosts = ko.observableArray()
		clear(){
			Init.model.clienthosts.removeAll()
		}
	}
}

module Keys {
	export class URL {
		static hosts = "/mock/host.json"
		static guests = "/mock/guest_"
	}
}

class Net {
	static connect(url:string) {
		$.getJSON(url, function(data:any){
			Init.model.esxhosts.removeAll();
			_(data).each(function(host){
				Init.model.esxhosts.push(new Model.EHost(host.name))
			})
		})
	}
	static guest(url:string){
		$.getJSON(url, function(data:any){
			Init.model.clienthosts.removeAll();
			_(data).each(function(host){
				Init.model.clienthosts.push(new Model.CHost(host.name, host.power, host.full))
			})
		})
	}
}

// initializer
class Init {
	static model:Model.Vmodel
	constructor(){
		Init.model = new Model.Vmodel();

		ko.applyBindings(Init.model);
		Net.connect(Keys.URL.hosts);
	}
}


$(() => new Init());