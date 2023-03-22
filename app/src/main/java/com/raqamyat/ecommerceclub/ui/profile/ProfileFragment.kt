package com.raqamyat.ecommerceclub.ui.profile

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.tabs.TabLayout
import com.raqamyat.ecommerceclub.MainActivity
import com.raqamyat.ecommerceclub.R
import com.raqamyat.ecommerceclub.base.BaseFragment
import com.raqamyat.ecommerceclub.data.Constants.REQUEST_PICK_IMAGE
import com.raqamyat.ecommerceclub.databinding.FragmentProfileBinding
import com.raqamyat.ecommerceclub.databinding.HomeFragmentBinding
import com.raqamyat.ecommerceclub.ui.auth.UserData
import com.raqamyat.ecommerceclub.ui.profile.viewModels.ProfileViewModel
import com.raqamyat.ecommerceclub.utilities.ImageHelper
import com.raqamyat.ecommerceclub.utilities.PermissionUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.util.*

@AndroidEntryPoint
class ProfileFragment : BaseFragment() {
    private lateinit var binding: FragmentProfileBinding
    private val viewModel: ProfileViewModel by viewModels()
    private var shouldRefreshOnResume = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!shouldRefreshOnResume) {
            init()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        if (!shouldRefreshOnResume) {
            binding = FragmentProfileBinding.inflate(inflater, container, false)
        }
        return binding.root
    }

    private fun init() {
        initTabLayout()
        loadImage()
        errorMessage()
        requestResponse()
        logout()
        logoutResponse()
        binding.editImage.setOnClickListener { pickImage() }
    }

    private fun initTabLayout() {
        binding.profileTabLayout.id = binding.profileTabLayout.id+ Random().nextInt(10)

        binding.profileTabLayout.addTab(
            binding.profileTabLayout.newTab().setText(getString(R.string.account_info))
        )
        binding.profileTabLayout.addTab(binding.profileTabLayout.newTab().setText(getString(R.string.password)))
        binding.profileTabLayout.addTab(
            binding.profileTabLayout.newTab().setText(getString(R.string.certificates))
        )
        binding.profileTabLayout.tabGravity = TabLayout.GRAVITY_FILL

        binding.profileViewPager.id = binding.profileViewPager.id+ Random().nextInt(10)
        binding.profileViewPager.adapter = ProfilePagerAdapter(requireActivity().supportFragmentManager)
        binding.profileViewPager.currentItem = 0
        binding.profileViewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.profileTabLayout))
        binding.profileTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                binding.profileViewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    private fun loadImage() {
        binding.profileImage.clipToOutline = true
        ImageHelper().loadImage(
            requireActivity(),
            getPreferencesHelper().getString("image"),
            R.drawable.profile_icon,
            binding.profileImage
        )
    }

    private fun pickImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_PICK_IMAGE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        pickImage()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            val selectedImageUri = data?.data
            val selectedImagePath = getRealPathFromURI(selectedImageUri)
            val file = File(selectedImagePath)
            val requestFile = RequestBody.create("image/*".toMediaTypeOrNull(), file)
            val imageBody = MultipartBody.Part.createFormData("image", file.name, requestFile)
            binding.profileImage.setImageURI(selectedImageUri)
            uploadImage(imageBody)
        }
    }

    private fun getRealPathFromURI(uri: Uri?): String {
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = requireActivity().contentResolver.query(uri!!, projection, null, null, null)
        val columnIndex = cursor?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor?.moveToFirst()
        val imagePath = columnIndex?.let { cursor?.getString(it) }
        cursor?.close()
        return imagePath ?: ""
    }

    private fun uploadImage(image: MultipartBody.Part) {
        showLoading()
        viewModel.uploadProfileImage(image)
    }

    private fun errorMessage() {
        lifecycleScope.launch {
            viewModel.errorMessage.observe(viewLifecycleOwner) {
                showErrorDialog(it.toString())
            }
        }
    }

    private fun requestResponse() {
        lifecycleScope.launch {
            viewModel.response.observe(viewLifecycleOwner) {
                dismissLoading()
                showErrorDialog(it?.message.toString())
            }
        }
    }

    private fun logout(){
        binding.logout.setOnClickListener {
            showLoading()
            viewModel.logout()
        }
    }

    private fun logoutResponse() {
        lifecycleScope.launch {
            viewModel.logoutResponse.observe(viewLifecycleOwner) {
                dismissLoading()
                UserData(requireActivity()).logout()
                val intent = Intent(requireActivity(), MainActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            }
        }
    }
    override fun onPause() {
        super.onPause()
        shouldRefreshOnResume = true
    }

}

