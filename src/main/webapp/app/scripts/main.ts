/// <reference path="d/jquery.d.ts" />
/// <reference path="d/knockout.d.ts" />
/// <reference path="d/underscore.d.ts" />

module Model {
	export class EHost {
		constructor(data:any){
			this.name(data.name)
			Net.guest(Keys.URL.guests + this.name(), this.child, Net.chost);
		}
		name = ko.observable()
		child = ko.observableArray()
		getChildren(){
			Init.model.svname(this.name())
			Net.guest(Keys.URL.guests + this.name(), Init.model.clienthosts, Net.chost);
		}
	}
	export class CHost {
		constructor(data:any){
//		constructor(name:string, power:boolean, full:string){
			this.name(data.name)
			this.power(data.isOn)
			this.full(data.full)
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
		static hosts  = "/api/hosts"
		static guests = "/api/guests/"
		static state  = "/api/state"
	}
}

class Net {
	static host(url:string, list:KnockoutObservableArray<any>, create:any) {
		$.getJSON(url, function(data:any){
			list.removeAll();
			_(data.success.data).each(function(host){
				list.push(create(host))
			})
		})
	}
	static guest(url:string, list:KnockoutObservableArray<any>, create:any){
		$.getJSON(url, function(data:any){
			list.removeAll();
			_(data.success.data).each(function(host){
				list.push(create(host))
			})
		})
	}

	static ehost = (host:any) => new Model.EHost(host)
	static chost = (host:any) => new Model.CHost(host)
}

// initializer
class Init {
	static DEBUG:boolean = false
	static model:Model.Vmodel
	constructor(){
		Init.model = new Model.Vmodel();
		ko.applyBindings(Init.model);
		Net.host(Keys.URL.hosts, Init.model.esxhosts, Net.ehost);
	}
}

$(() => new Init());