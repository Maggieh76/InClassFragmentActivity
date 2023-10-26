package edu.temple.inclassactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    lateinit var images : Array<Int>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imagesViewModel = ViewModelProvider(this)[ImagesViewModel::class.java]












        // Fetch images into IntArray called imageArray
        val typedArray = resources.obtainTypedArray(R.array.image_ids)
        val imageArray = IntArray(typedArray.length()) {typedArray.getResourceId(it, 0)}
        typedArray.recycle()

        imagesViewModel.setImages(imageArray)






        // Attach an instance of ImageDisplayFragment using factory method
        findViewById<Button>(R.id.button).setOnClickListener {
            (supportFragmentManager.findFragmentById(R.id.container) as ImageDisplayFragment).setImages(imageArray)
        }

        if(supportFragmentManager.findFragmentById(R.id.container) !is ImageDisplayFragment) //checks if fragment is already in container,
                                                                                // don't add twice, only goes through if it's not in there
            supportFragmentManager
                .beginTransaction() //start transaction
                .add(R.id.container, ImageDisplayFragment()) //add fragment to container
                .addToBackStack(null) //add transaction to backstack
                .setReorderingAllowed(true)
                .commit()
         //reference to fragment in container and then cast it

        //supportFragmentManager.beginTransaction().add(R.id.container, ImageDisplayFragment.newInstance(imageArray)).commit()
    }


}
