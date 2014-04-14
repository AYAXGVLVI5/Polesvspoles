/**
 * GIBS Web Examples
 *
 * Copyright 2013 - 2014 United States Government as represented by the
 * Administrator of the National Aeronautics and Space Administration.
 * All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

window.onload = function() {

    var EPSG3413 = new L.Proj.CRS(
        "EPSG:3413",
        "+proj=stere +lat_0=90 +lat_ts=70 +lon_0=-45 +k=1 +x_0=0 +y_0=0 " +
        "+ellps=WGS84 +datum=WGS84 +units=m +no_defs", {
            origin: [-4194304, 4194304],
            resolutions: [
                8192.0,
                4096.0,
                2048.0,
                1024.0,
                512.0,
                256.0
            ],
            bounds: [
                [-4194304, -4194304],
                [4194304, 4194304]
            ]
        }
    );

    var map = L.map("map", {
        center: [90, 0],
        zoom: 0,
        maxZoom: 5,
        crs: EPSG3413
    });

    var template =
        "https://map1{s}.vis.earthdata.nasa.gov/wmts-arctic/" +
        "{layer}/default/{time}/{tileMatrixSet}/{z}/{y}/{x}.jpg";

    var a=["2012-01-05","2012-02-01","2012-03-01","2012-04-01","2012-05-01","2012-06-01","2012-07-01","2012-08-01","2012-09-01","2012-10-01","2012-11-01","2012-12-01","2013-01-01","2013-03-01","2013-04-01","2013-05-01","2013-06-01","2013-07-01","2013-08-01","2013-09-01","2013-10-01","2013-11-01","2013-12-01","2014-01-01" ];

    var layer = L.tileLayer(template, {
        layer: "MODIS_Terra_CorrectedReflectance_TrueColor",
        tileMatrixSet: "EPSG3413_250m",
        time: a[10],
        tileSize: 512,
        subdomains: "abc",
        noWrap: true,
        continuousWorld: true,
        attribution:
            "<a href='https://earthdata.nasa.gov/gibs'>" +
            "NASA EOSDIS GIBS</a>&nbsp;&nbsp;&nbsp;" +
            "<a href='https://github.com/nasa-gibs/web-examples/blob/release/leaflet/js/arctic-epsg3413.js'>" +
            "View Source" +
            "</a>"
    });
var layer2 = L.tileLayer(template, {
        layer: "MODIS_Terra_CorrectedReflectance_TrueColor",
        tileMatrixSet: "EPSG3413_250m",
        time: a[11],
        tileSize: 512,
        subdomains: "abc",
        noWrap: true,
        continuousWorld: true,
        attribution:
            "<a href='https://earthdata.nasa.gov/gibs'>" +
            "NASA EOSDIS GIBS</a>&nbsp;&nbsp;&nbsp;" +
            "<a href='https://github.com/nasa-gibs/web-examples/blob/release/leaflet/js/arctic-epsg3413.js'>" +
            "View Source" +
            "</a>"
    });
var layer3 = L.tileLayer(template, {
        layer: "MODIS_Terra_CorrectedReflectance_TrueColor",
        tileMatrixSet: "EPSG3413_250m",
        time: a[12],
        tileSize: 512,
        subdomains: "abc",
        noWrap: true,
        continuousWorld: true,
        attribution:
            "<a href='https://earthdata.nasa.gov/gibs'>" +
            "NASA EOSDIS GIBS</a>&nbsp;&nbsp;&nbsp;" +
            "<a href='https://github.com/nasa-gibs/web-examples/blob/release/leaflet/js/arctic-epsg3413.js'>" +
            "View Source" +
            "</a>"
    });
var layer4 = L.tileLayer(template, {
        layer: "MODIS_Terra_CorrectedReflectance_TrueColor",
        tileMatrixSet: "EPSG3413_250m",
        time: a[21],
        tileSize: 512,
        subdomains: "abc",
        noWrap: true,
        continuousWorld: true,
        attribution:
            "<a href='https://earthdata.nasa.gov/gibs'>" +
            "NASA EOSDIS GIBS</a>&nbsp;&nbsp;&nbsp;" +
            "<a href='https://github.com/nasa-gibs/web-examples/blob/release/leaflet/js/arctic-epsg3413.js'>" +
            "View Source" +
            "</a>"
    });
var layer5 = L.tileLayer(template, {
        layer: "MODIS_Terra_CorrectedReflectance_TrueColor",
        tileMatrixSet: "EPSG3413_250m",
        time: a[22],
        tileSize: 512,
        subdomains: "abc",
        noWrap: true,
        continuousWorld: true,
        attribution:
            "<a href='https://earthdata.nasa.gov/gibs'>" +
            "NASA EOSDIS GIBS</a>&nbsp;&nbsp;&nbsp;" +
            "<a href='https://github.com/nasa-gibs/web-examples/blob/release/leaflet/js/arctic-epsg3413.js'>" +
            "View Source" +
            "</a>"
    });
var layer6 = L.tileLayer(template, {
        layer: "MODIS_Terra_CorrectedReflectance_TrueColor",
        tileMatrixSet: "EPSG3413_250m",
        time: a[23],
        tileSize: 512,
        subdomains: "abc",
        noWrap: true,
        continuousWorld: true,
        attribution:
            "<a href='https://earthdata.nasa.gov/gibs'>" +
            "NASA EOSDIS GIBS</a>&nbsp;&nbsp;&nbsp;" +
            "<a href='https://github.com/nasa-gibs/web-examples/blob/release/leaflet/js/arctic-epsg3413.js'>" +
            "View Source" +
            "</a>"
    });


    // HACK: BEGIN
    // Leaflet does not yet handle these kind of projections nicely. Monkey
    // patch the getTileUrl function to ensure requests are within
    // tile matrix set boundaries.
var baseMaps = {
      "Nov_2012": layer,
      "Dec_2012": layer2,
      "Jan_2013": layer3,
      "Nov_2013": layer4,
      "Dec_2013": layer5,
      "Jan_2014": layer6
    };
    var superGetTileUrl = layer.getTileUrl;

    layer.getTileUrl = function(coords) {
        var max = Math.pow(2, layer._getZoomForUrl() + 1);
        if ( coords.x < 0 ) { return ""; }
        if ( coords.y < 0 ) { return ""; }
        if ( coords.x >= max ) { return ""; }
        if ( coords.y >= max ) { return ""; }
        return superGetTileUrl.call(layer, coords);
    };
    // HACK: END

    map.addLayer(layer);
    L.control.layers(baseMaps).addTo(map);

};

