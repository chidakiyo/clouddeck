/// <reference path="d/jquery.d.ts" />
/// <reference path="d/knockout.d.ts" />
/// <reference path="d/underscore.d.ts" />

module Model {
	export class EHost {
		constructor(data:any){
			MappingUtil.copy(data, this)
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
			return ((!!this.nickname()) ? StringUtil.deco(this.nickname()) + ' ' : '') + this.name()
		}
	}
	export class CHost {
		constructor(data:any){
			MappingUtil.copy(data, this)
			this.isOn(data.isOn)
		}
		name:KnockoutObservable<string> = ko.observable("")
		ip:KnockoutObservable<string> = ko.observable("")
		host:KnockoutObservable<string> = ko.observable("")
		isOn:KnockoutObservable<boolean> = ko.observable(false)
		fullPath:KnockoutObservable<string> = ko.observable("")
		vmwareToolsStatus:KnockoutObservable<string> = ko.observable("")
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
class MappingUtil {
	static copy<T>(json:any, model:T):T {
		_(json).map((v,k) => {
			if(!_(model[k]).isUndefined()){
				model[k](json[k])
			}
		})
		return model
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
	static model = new Model.Vmodel()
	constructor(){
		$.ajaxSetup({ cache: false }); // disable cache
		ko.applyBindings(Init.model)
		Net.host(Init.model.esxhosts)
	}
}

$(() => new Init())