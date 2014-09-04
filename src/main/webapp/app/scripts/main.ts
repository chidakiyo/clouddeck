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
			Init.model.svname(this.name())
			Net.guest(Keys.URL.url().guests + this.name());
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

		private static urlmaster:any = null

		static url():any{
			if(URL.urlmaster == null){
				if(Init.LIVE == true){
					URL.urlmaster = URL.prod
					return URL.urlmaster
				} else {
					URL.urlmaster = URL.test
					return URL.urlmaster
				}
			} else {
				return URL.urlmaster
			}
		}

		private static prod = {
			hosts : "hosts",
			guests : "guests/",
			state : "state"
		}

		private static test = {
			hosts : "/mock/host.json",
			guests : "/mock/guest_",
			state : "/mock/state"
		}
	}
}

class Net {
	static connect(url:string) {
		$.getJSON(url, function(data:any){
			Init.model.esxhosts.removeAll();
			_(data.success.data).each(function(host){
				Init.model.esxhosts.push(new Model.EHost(host.name))
			})
		})
	}
	static guest(url:string){
		$.getJSON(url, function(data:any){
			console.log(data)
			Init.model.clienthosts.removeAll();
			_(data.success.data).each(function(host){
				Init.model.clienthosts.push(new Model.CHost(host.name, host.isOn, host.full))
			})
		})
	}
}

// initializer
class Init {
	static LIVE:boolean = false
	static DEBUG:boolean = false
	static model:Model.Vmodel
	constructor(){
		Init.model = new Model.Vmodel();
		ko.applyBindings(Init.model);
		Net.connect(Keys.URL.url().hosts);
	}
}


$(() => new Init());