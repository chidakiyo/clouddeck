/// <reference path="d/jquery.d.ts" />
/// <reference path="d/knockout.d.ts" />
/// <reference path="d/underscore.d.ts" />

module Model {
	export class EHost {
		constructor(data:any){
			this.name(data.name) // json -> prop
			this.nickname(data.nickname) // json -> prop
			this.description(data.description) // json -> prop
			Net.guest(this.name(), this.child)
		}
		name:KnockoutObservable<string> = ko.observable("")
		nickname:KnockoutObservable<string> = ko.observable("")
		description:KnockoutObservable<string> = ko.observable("")
		child = ko.observableArray()
		getChildren(){
			Init.model.svname(this.name())
			Net.guest(this.name(), Init.model.clienthosts)
			Init.model.searchfor("")
		}
		toHost(){
			return ((this.nickname() != null) ? StringUtil.deco(this.nickname()) + ' ' : '') + this.name()
		}
	}
	export class CHost {
		constructor(data:any){
			this.name(data.name)
			this.power(data.isOn)
			this.full(data.fullPath)
			this.tools(data.vmwareToolsStatus)
		}
		name = ko.observable()
		power = ko.observable()
		full = ko.observable()
		tools = ko.observable()
		searchChildren(){
			return -1 !== new String(this.name()).toUpperCase().indexOf(Init.model.searchfor().toUpperCase());
		}
	}

	export class Vmodel {
		esxhosts = ko.observableArray()
		svname = ko.observable()
		clienthosts = ko.observableArray()
		searchfor = ko.observable("")
		clear(){
			Init.model.clienthosts.removeAll()
			Init.model.searchfor("")
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

class StringUtil {
  	static deco(word:string){return "[ " + word + " ]"}
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
	static model = new Model.Vmodel()
	constructor(){
		$.ajaxSetup({ cache: false }); // disable cache
		ko.applyBindings(Init.model)
		Net.host(Init.model.esxhosts)
	}
}

$(() => new Init())