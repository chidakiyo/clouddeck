/// <reference path="d/jquery.d.ts" />
/// <reference path="d/knockout.d.ts" />
/// <reference path="d/underscore.d.ts" />

module Model {
	export class EHost {
		constructor(data:any){
			this.name(data.name)
			Net.guest(this.name(), this.child)
		}
		name:KnockoutObservable<string> = ko.observable("")
		child = ko.observableArray()
		getChildren(){
			Init.model.svname(this.name())
			Net.guest(this.name(), Init.model.clienthosts)
		}
	}
	export class CHost {
		constructor(data:any){
			this.name(data.name)
			this.power(data.isOn)
			this.full(data.full)
		}
		name = ko.observable()
		power = ko.observable()
		full = ko.observable()
		searchChildren(){
			console.log(Init.model.searchfor())
			return -1 !== new String(this.name()).indexOf("A");
		}
	}

	export class Vmodel {
		esxhosts = ko.observableArray()
		svname = ko.observable()
		clienthosts = ko.observableArray()
		searchfor = ko.observable()
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
	static host(list:KnockoutObservableArray<any>) {
		list.removeAll()
		$.getJSON(Keys.URL.hosts)
		 .done((data:any) => data.success.data.map(host => list.push(Net.ehost(host))))
	}
	static guest(url:string, list:KnockoutObservableArray<any>){
		list.removeAll()
		$.getJSON(Keys.URL.guests + url)
		 .done((data:any) => data.success.data.map(host => list.push(Net.chost(host))))
	}
	
	static ehost = (host:any) => new Model.EHost(host)
	static chost = (host:any) => new Model.CHost(host)
}

// initializer
class Init {
	static DEBUG:boolean = false
	static model:Model.Vmodel
	// query = ko.observable('')
	constructor(){
		Init.model = new Model.Vmodel()
		ko.applyBindings(Init.model)
		Net.host(Init.model.esxhosts)
//		Net.host(Keys.URL.hosts, Init.model.esxhosts, Net.ehost);
//		Init.query.subscribe(Init.search);
		
	}

}

$(() => new Init())
