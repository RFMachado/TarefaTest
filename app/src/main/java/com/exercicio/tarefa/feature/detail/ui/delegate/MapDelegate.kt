package com.exercicio.tarefa.feature.detail.ui.delegate

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.exercicio.tarefa.R
import com.exercicio.tarefa.feature.detail.domain.entity.MapData
import com.exercicio.tarefa.util.extensions.inflate
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.hannesdorfmann.adapterdelegates3.AbsListItemAdapterDelegate
import kotlinx.android.synthetic.main.item_delegate_map.view.*


class MapDelegate: AbsListItemAdapterDelegate<MapData, Any, MapDelegate.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view),
        OnMapReadyCallback {

        private val layout: View = view
        private val mapView: MapView = layout.findViewById(R.id.mapView)

        private lateinit var map: GoogleMap
        private lateinit var latLng: LatLng

        init {
            with(mapView) {
                onCreate(null)
                getMapAsync(this@ViewHolder)
            }
        }

        override fun onMapReady(googleMap: GoogleMap?) {
            MapsInitializer.initialize(itemView.context.applicationContext)

            map = googleMap?: return

            setMapLocation()
        }

        fun bind(data: MapData): Unit = with(itemView) {
            val detail = data.detail
            mapView.tag = this

            latLng = LatLng(detail.lat, detail.lng )

            txtAddess.text = detail.address

            setMapLocation()
        }

        private fun setMapLocation() {
            if (!::map.isInitialized) return

            with(map) {
                uiSettings.setAllGesturesEnabled(false)
                uiSettings.isMapToolbarEnabled = false
                moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15.5f))
                addMarker(MarkerOptions().position(latLng))
                mapType = GoogleMap.MAP_TYPE_NORMAL

                setOnMarkerClickListener {
                    true
                }

                setOnMapClickListener {

                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup) = ViewHolder(parent.inflate(R.layout.item_delegate_map))
    override fun isForViewType(item: Any, items: MutableList<Any>, position: Int) = item is MapData
    override fun onBindViewHolder(item: MapData, viewHolder: ViewHolder, payloads: MutableList<Any>) = viewHolder.bind(item)

}