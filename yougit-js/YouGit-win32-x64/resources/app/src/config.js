const remote = require('electron').remote;
const app = remote.app;
const appDataPath = app.getPath('appData')
const fs = require('fs')
const storage = require('electron-json-storage')

module.exports = {

  getConfig: () => {
    return new Promise((resolve, reject) => {
      storage.get('config', (err, data) => {
        if(err) resolve(null)
        else if(Object.keys(data).length === 0) resolve(null)
        else resolve(data)
      })
    })
  },

  set: (data) => {
    return new Promise((resolve, reject) => {
      storage.set('config', data, (err) => {
        if(err) reject(err)
        else resolve()
      })
    })
  }

}