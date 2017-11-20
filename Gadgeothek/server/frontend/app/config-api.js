const BASEURL = "http://feature-config.yvu.ch/config";

export default class ConfigApi {
    static get() {
        return fetch(BASEURL)
          .then(result => result.json());
    }

    static set(feature, isEnabled) {
      fetch(BASEURL + '/' + feature, {
        method: 'PUT',
        body: isEnabled.toString()
      }).then(result => console.log('Got result ' + result));
    }
}

class ConfigView extends HTMLElement {
	createdCallback() {
    	console.log('Fetching config')
    	ConfigApi.get().then(data => this.render(data));
  	}

  	render(data) {
    	this.innerHTML = `<div class="config-container">`
      for (var feature in data) {
        if (data.hasOwnProperty(feature)) {
            this.innerHTML += this.renderEntry(feature, data[feature]);
        }
      }
      this.innerHTML += `</div>`;
  }

  renderEntry(feature, isEnabled) {
    window.featureSet = function(feature){
      ConfigApi.set(feature, document.getElementById(feature).checked);
    }
    return `
      <input type="checkbox" id="${feature}" ${isEnabled ? "checked" : ""} onClick="featureSet('${feature}')"/> ${feature} <br />
    `;
  }
}

export default document.registerElement('config-view', {
  prototype: ConfigView.prototype
});
